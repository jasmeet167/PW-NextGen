package com.csc.fsg.life.environments;


/**
   Represents a MQ Connection.
**/
public class MQConnectionInfo
	extends AbstractConnection
{
	
	// MQ Parameters
	private String m_mqSendQueueName;
	private String m_mqManagerName;
	private String m_mqReceiveQueueName;
	private String m_mqPort;
	private String m_mqServerChannel;
	private String m_mqHostName;

	/**
	   Creates a MQ connection object.
	   @param mqSendQueueName
	   @param mqManagerName
	   @param mqReceiveQueueName
	   @param mqPort
	   @param mqServerChannel
	   @param mqHostName
	 */
	public MQConnectionInfo(
		String mqSendQueueName,
		String mqManagerName,
		String mqReceiveQueueName,
		String mqPort,
		String mqServerChannel,
		String mqHostName) {
		m_mqHostName = mqHostName;
		m_mqManagerName = mqManagerName;
		m_mqReceiveQueueName = mqReceiveQueueName;
		m_mqPort = mqPort;
		m_mqServerChannel = mqServerChannel;
		m_mqSendQueueName = mqSendQueueName;
	}

	/**
	   Returns the MqHostName.
	   @return The MqHostName value.
	   @see #setMqHostName
	 **/
	public String getMqHostName() {
		return m_mqHostName;
	}

	/**
	   The MQ Manager name.
	   @return String
	 */
	public String getMqManagerName() {
		return m_mqManagerName;
	}

	/**
	   The MQ port to use.
	   @return String
	 */
	public String getMqPort() {
		return m_mqPort;
	}

	/**
	   The MQ Receive Queue name.
	   @return String
	 */
	public String getMqReceiveQueueName() {
		return m_mqReceiveQueueName;
	}

	/**
	   The MQ Send queue name.
	   @return String
	 */
	public String getMqSendQueueName() {
		return m_mqSendQueueName;
	}

	/**
	   The MQ server channel
	   @return String
	 */
	public String getMqServerChannel() {
		return m_mqServerChannel;
	}

	/**
	   Sets the MqHostName.
	   @param string The new MqHostName value.
	   @see #getMqHostName
	 **/
	public void setMqHostName(String string) {
		m_mqHostName = string;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("ConnInfo: Environment Protocol: ");
		sb.append("MQ");

		sb.append(", MQ Request Queue Name ");
		sb.append(m_mqSendQueueName);
		sb.append(", MQ Manager Name ");
		sb.append(m_mqManagerName);
		sb.append(", MQ Response Queue Name ");
		sb.append(m_mqReceiveQueueName);
		sb.append(", MQ Port ");
		sb.append(m_mqPort);
		sb.append(", MQ Server Channel ");
		sb.append(m_mqServerChannel);
		sb.append(", MQ Host Name ");
		sb.append(m_mqHostName);
		sb.append(", charset ");
		sb.append(getCharset());

		return sb.toString();
	}

}
