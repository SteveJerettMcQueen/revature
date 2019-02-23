package database;

import org.junit.Assert;
import org.junit.Test;

import util.DBUtil;

public class TestDataSourceConnection {

	@Test
	public void testPosgresDataSource() {
		Assert.assertNotNull(DBUtil.getPostgresDataSource());
	}

}
