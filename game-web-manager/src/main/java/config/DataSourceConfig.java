package config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import lombok.Data;

@Data
//@ConfigurationProperties(prefix = "db.properties")
public class DataSourceConfig
{
	private String driver;

	//@Bean()// 作用是当数据库连接不使用的时候,就把该连接重新放到数据池中,方便下次使用调用.
	public DataSource dataSource()
	{

		return null;
	}
}
