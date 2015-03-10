package com.learning.activiti.ldap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;


public class QueryActiveDirectory {

	private Properties properties;
	private Hashtable<String, String> env;
	private String[] defaultLanIdSearchAttributes = { "givenName", "sn",
			"manager", "initials", "mail", "telephoneNumber", "sAMAccountName" };
	private String[] defaultCNSearchAttributes = { "givenName", "sn",
			"manager", "initials", "mail", "telephoneNumber", "sAMAccountName" };
	private static final String SEARCH_BASE = "DC=ent,DC=wfb,DC=bank,DC=corp";
	private static final String CN_FILTER = "(&(objectCategory=user)(cn=%s))";
	private static final String LAN_ID_FILTER = "(&(objectCategory=user)(sAMAccountName=%s))";

	/**
	 * Instantiates a QueryActiveDirectory with the specified properties and 
	 * default search attributes for both CN and LanId searches.
	 * 
	 * @param properties
	 *            LDAP connection properties
	 */
	public QueryActiveDirectory(Properties properties) {
		super();
		//@TODO error checking if properties == null/invalid?
		this.properties = properties;
	}

	/**
	 * Instantiates a new QueryActiveDirectory with the specified properties, 
	 * and uses the specified set of search attributes for both CN and LanId
	 * searches.
	 * 
	 * @param properties
	 *            LDAP connection properties
	 * @param defaultSearchAttributes
	 *            the default search attributes for both LanId and CN searches
	 */
	public QueryActiveDirectory(Properties properties, String[] defaultSearchAttributes) {
		super();
		//@TODO: validate properties and search attributes...
		this.properties = properties;
		this.defaultLanIdSearchAttributes = defaultSearchAttributes;
		this.defaultCNSearchAttributes = defaultSearchAttributes;
	}

	/**
	 * Instantiates a new QueryActiveDirectory.
	 * 
	 * @param properties
	 *            LDAP connection properties
	 * @param defaultLanIdSearchAttributes
	 *            Search attributes to return for LanId searches
	 * @param defaultCNSearchAttributes
	 *            Search attributes to return for CN searches
	 */
	public QueryActiveDirectory(Properties properties,
			String[] defaultLanIdSearchAttributes,
			String[] defaultCNSearchAttributes) {
		super();
		//@TODO: validate properties and search attributes...
		this.properties = properties;
		this.defaultLanIdSearchAttributes = defaultLanIdSearchAttributes;
		this.defaultCNSearchAttributes = defaultCNSearchAttributes;
	}
	
	/**
	 * Federated search by LanId.  Returns data for the user and his/her manager
	 * in one map so clients don't need to make a second query.
	 * 
	 * @param lanId
	 *            the LanId to search for
	 * @return the map of attributes and values
	 */
	public Map<String, String> federatedSearchByLanId(String lanId) {
		Map<String, String> results = searchByLanId(lanId);
		
		//@TODO Error checking: managerCN == null?
		String managerCN = results.get("manager").split(",")[0].replace("CN=", "");
		Map<String, String> managerData = searchByCN(managerCN);
		results.put("managerName", managerCN);
		results.put("managerPhone", managerData.get("telephoneNumber"));
		results.put("managerLanId", managerData.get("sAMAccountName"));
		results.put("managerEmail", managerData.get("mail"));
		
		return results;
	}

	/**
	 * Search and return the default set of attributes by LanId.
	 * 
	 * @param lanId
	 *            the LanId to search for
	 * @return map of attributes and retrieved values
	 */
	public Map<String, String> searchByLanId(String lanId) {
		return searchByLanId(lanId, this.defaultLanIdSearchAttributes);
	}

	/**
	 * Search and return the specified set of attributes by LanId.
	 * 
	 * @param lanId
	 *            the LanId to search for
	 * @param returnedAttributes
	 *            the attributes to return
	 * @return the map of attributes and retrieved values
	 */
	public Map<String, String> searchByLanId(String lanId,
			String[] returnedAttributes) {
		return searchAD(String.format(LAN_ID_FILTER, lanId), returnedAttributes);
	}

	/**
	 * Search by cn.
	 * 
	 * @param cn
	 *            the cn to search for
	 * @return the map of attributes and retrieved values
	 */
	public Map<String, String> searchByCN(String cn) {
		return searchByCN(cn, this.defaultCNSearchAttributes);
	}

	/**
	 * Search by cn.
	 * 
	 * @param cn
	 *            the cn to search for
	 * @param returnedAttributes
	 *            the attributes to return
	 * @return the map of attributes and retrieved values
	 */
	public Map<String, String> searchByCN(String cn, String[] returnedAttributes) {
		return searchAD(String.format(CN_FILTER, cn), returnedAttributes);
	}

	/**
	 * Search ad.
	 * 
	 * @param filter
	 *            the filter
	 * @param returnedAttributes
	 *            the returned attributes
	 * @return the map
	 */
	private Map<String, String> searchAD(String filter, String[] returnedAttributes) {
		Map<String, String> results = new HashMap<String, String>(returnedAttributes.length);

		if (env == null) {
			env = new Hashtable<String, String>(properties.size());
			for (final String name : properties.stringPropertyNames()) {
				env.put(name, properties.getProperty(name));
			}
		}
		SearchControls searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchCtls.setReturningAttributes(returnedAttributes);

		try {
			DirContext ctx = new InitialLdapContext(env, null);
			NamingEnumeration<SearchResult> searchResult = ctx.search(SEARCH_BASE,
					filter, searchCtls);
			Attributes a = searchResult.next().getAttributes();
			
			//@TODO Should we consider returning a Map<String, Object> instead? 
			for(String key: returnedAttributes) {
				results.put(key, String.valueOf(a.get(key).get()));
			}
			
		} catch (NamingException ne) {
			ne.printStackTrace();
            //@TODO thorow exception
        }

		return results;
	}

	/**
	 * Gets the default CN search attributes.
	 * 
	 * @return the default CN search attributes
	 */
	public String[] getDefaultCNSearchAttributes() {
		return defaultCNSearchAttributes;
	}

	/**
	 * Gets the default LanId search attributes.
	 * 
	 * @return the default LanID search attributes
	 */
	public String[] getDefaultLanIdSearchAttributes() {
		return defaultLanIdSearchAttributes;
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the default CN search attributes.
	 * 
	 * @param defaultManagerAttributes
	 *            the new default manager search attributes
	 */
	public void setDefaultCNSearchAttributes(
			String[] defaultManagerAttributes) {
		defaultCNSearchAttributes = defaultManagerAttributes;
	}

	/**
	 * Sets the default LanID search attributes.
	 * 
	 * @param defaultUserAttributes
	 *            the new default user search attributes
	 */
	public void setDefaultLanIdSearchAttributes(String[] defaultUserAttributes) {
		defaultLanIdSearchAttributes = defaultUserAttributes;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties
	 *            the new properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
