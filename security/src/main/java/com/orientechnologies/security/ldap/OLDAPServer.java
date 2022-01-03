/**
 * Copyright 2010-2016 OrientDB LTD (http://orientdb.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * For more information: http://www.orientdb.com
 */
package com.orientechnologies.security.ldap;

import java.net.URI;
import java.net.URISyntaxException;

public class OLDAPServer
{
	private String _Scheme, _Host;
	private int _Port;
	private boolean _IsAlias;
	private String _Principal;
	private String _Credentials;
	
	public String getHostname() { return _Host; }

	public String getURL()
	{
		return String.format("%s://%s:%d", _Scheme, _Host, _Port);
	}

	// Replaces the current URL's host port with hostname and returns it.
	public String getURL(final String hostname)
	{
		return String.format("%s://%s:%d", _Scheme, hostname, _Port);
	}

	public boolean isAlias() { return _IsAlias; }

	public String getPrincipal() { return _Principal; }
	public String getCredentials() { return _Credentials; }

	public OLDAPServer(final String scheme, final String host, int port, boolean isAlias, final String principal, final String credentials)
	{
		_Scheme = scheme;
		_Host = host;
		_Port = port;
		_IsAlias = isAlias;
		_Principal = principal;
		_Credentials = credentials;
	}
	
	public static OLDAPServer validateURL(final String url, boolean isAlias, final String principal, final String credentials)
	{
		OLDAPServer server = null;
		
		try
		{
			URI uri = new URI(url);
			
			String scheme 	= uri.getScheme();
			String host 	= uri.getHost();
			int port 		= uri.getPort();
			if(port == -1) port = 389; // Default to standard LDAP port.
			
			server = new OLDAPServer(scheme, host, port, isAlias, principal, credentials);
		}
		catch(URISyntaxException se)
		{
			
		}
		
		return server;
	}
	
}
