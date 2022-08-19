package tests

import java.sql.{Connection,DriverManager}

object ScalaJdbcConnectSelect extends App{
  // connect to the database named "mysql" on port 8889 of localhost
  val url = "jdbc:phoenix:ip-13-0-5-207.ec2.internal:2181:/hbase:tdss@TOOKITAKI.COM:/home/ec2-user/conf/tdss.keytab;schema=v393_db"
  val driver = "org.apache.phoenix.jdbc.PhoenixDriver"
  val username = ""
  val password = ""
  var connection:Connection = _
  try {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    val statement = connection.createStatement
    val rs = statement.executeQuery("SELECT * FROM v393_db.TM_ALERTS")
    while (rs.next) {
      val id = rs.getString("ALERT_ID")
      println(s"id: $id")
    }
  } catch {
    case e: Exception => e.printStackTrace
  }
  connection.close
}
