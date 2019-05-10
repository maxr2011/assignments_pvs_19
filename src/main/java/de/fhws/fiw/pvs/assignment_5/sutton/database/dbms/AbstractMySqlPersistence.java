/*
 * Copyright 2019 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.fhws.fiw.pvs.assignment_5.sutton.database.dbms;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractMySqlPersistence
{
	private ComboPooledDataSource cpds;

	protected AbstractMySqlPersistence( )
	{
		createConnectionPool( );
		createAllTables( );
	}

	public void createConnectionPool( )
	{
		try
		{
			Class.forName( "com.mysql.jdbc.Driver" );
			this.cpds = new ComboPooledDataSource( );
			this.cpds.setDriverClass( "com.mysql.jdbc.Driver" );
			this.cpds.setJdbcUrl( "jdbc:mysql.//localhost:3306/" + getDatabaseName( ) );
			this.cpds.setUser( "zikzak" );
			this.cpds.setPassword( "zikzak" );
			this.cpds.setMinPoolSize( 5 );
			this.cpds.setAcquireIncrement( 5 );
			this.cpds.setMaxPoolSize( 20 );
		}
		catch ( final Exception e )
		{

		}
	}

	protected abstract String getDatabaseName( );

	protected abstract void createAllTables( );

	public Connection getConnection( ) throws SQLException
	{
		return this.cpds.getConnection( );
	}

}
