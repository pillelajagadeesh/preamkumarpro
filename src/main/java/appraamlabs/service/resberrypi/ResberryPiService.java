package appraamlabs.service.resberrypi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import appraamlabs.models.SerfsBoard;
import appraamlabs.utils.constants.MessageConstants;

@Service
public class ResberryPiService {

	@Autowired
	Environment env;
	
	private Logger logger = LoggerFactory.getLogger(ResberryPiService.class);

	public void sendFileToResberrySsh(SerfsBoard serfsBoard) throws IOException, JSchException{
		
		logger.debug("Request to send serf data to resberry pi");
		
	    String host = env.getProperty(MessageConstants.SSH_HOST);
		logger.debug("SSH-HOST : "+env.getProperty(MessageConstants.SSH_HOST));
	    String user = env.getProperty(MessageConstants.SSH_USERNAME);
	    String password = env.getProperty(MessageConstants.SSH_PASSWORD);
	    int port = Integer.parseInt(env.getProperty(MessageConstants.SSH_PORT));
	    
	   // String gprsConn = null;
	    
	    /*if(serfsBoard.getGprsConnection()== true){
	    	gprsConn = "YES";
	    }else{
	    	gprsConn = "NO";
	    }*/
	    
	    	File file = new File("config.ini");
	    	
	    	FileWriter fileout = new FileWriter(file);
	    	BufferedWriter outStream = new BufferedWriter(fileout);
	        outStream.write("[FTPServer]");
	        outStream.newLine();
	        outStream.write("server 			 = http://"+serfsBoard.getFtpUrl()+"			;Server IP address");
	        outStream.newLine();
	        outStream.write("username 		 = "+serfsBoard.getUserName()+"		;Server UserName");
	        outStream.newLine();
	        outStream.write("password 		 = "+serfsBoard.getPassword()+"				;Server Password");	        
	        outStream.newLine(); outStream.newLine();
	        outStream.write("[UniqueID]");
	        outStream.newLine();
	        outStream.write("unique_id		 = "+serfsBoard.getUid()+"				;Unique ID for BLE reader");
	        outStream.newLine(); outStream.newLine();
	        outStream.write("[PushDataToFTP]");
	        outStream.newLine();
	        outStream.write("Time		       	 = "+serfsBoard.getFtpTimeIntervel()+"				;(**Note) Frequency of pushing data to FTP (minimum 3 min)");
	        outStream.newLine(); outStream.newLine();
	        outStream.write("[GPRS]");
	        outStream.newLine();
	        outStream.write("Gprs_connection		 = "+serfsBoard.getGprsConnection()+"				;YES/NO for GPRS connection");
	        outStream.newLine(); outStream.newLine();
	        outStream.write("[obd_data]");
	        outStream.newLine();
	        outStream.write("interval		 = "+serfsBoard.getObdTimeIntervel()+"				; 10 minutes interval to upload");
	        outStream.newLine(); outStream.newLine(); outStream.newLine();
	        outStream.write("[OBD]");
	        outStream.newLine();
	        outStream.write("protocol		 = "+serfsBoard.getObdProtocol());
	        outStream.newLine(); outStream.newLine();
	        outStream.write(";**Note:");
	        outStream.newLine();
	        outStream.write(";Timing is Approx. +/- 2Mins");
	        outStream.newLine();
	        outStream.write(";6 --> Every 3 Mins to FTP, 8 --> Every 4 Min to FTP, 10 --> Every 5 Min to FTP");
	        outStream.newLine(); outStream.newLine(); outStream.newLine(); outStream.newLine();
	        outStream.write(";Protocol");
	        outStream.newLine();
	        outStream.write(";0-Automatic");
	        outStream.newLine();
	        outStream.write(";1-SAE J1850 PWM");
	        outStream.newLine();
	        outStream.write(";2-SAE J1850 VPW");
	        outStream.newLine();
	        outStream.write(";3-ISO 9141-2");
	        outStream.newLine();
	        outStream.write(";4-ISO 14320-4 KWP");
	        outStream.newLine();
	        outStream.write(";5-ISO 14320-4 KWP");
	        outStream.newLine();
	        outStream.write(";6-ISO 15765-4 CAN(11-BIT,500 KBAUD)");
	        outStream.newLine();
	        outStream.write(";7-ISO 15765-4 CAN(29-BIT,500 KBAUD)");
	        outStream.newLine();
	        outStream.write(";8-ISO 15765-4 CAN(11-BIT,250 KBAUD)");
	        outStream.newLine();
	        outStream.write(";9-ISO 15765-4 CAN(29-BIT,250 KBAUD)");
	        outStream.newLine();
	        outStream.write(";A-SAE J1939 CAN(29-BIT)");
	        outStream.newLine();
	        outStream.close();
	        fileout.close();
	        
	        logger.debug("miscellaneous files saved.");
	        
	    	JSch jsch = new JSch();
			Session session = null;
			try {
				session = jsch.getSession(user, host, port);
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			logger.debug("Establishing Connection...");
			try {
				session.connect();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("Connection established.");
			logger.debug("Crating SFTP Channel.");
			ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
			try {
				sftpChannel.connect();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.debug("SFTP Channel created.");
			InputStream out= null;
			
	   try {
		   sftpChannel.cd(env.getProperty(MessageConstants.SSH_PATH));
			sftpChannel.put(new FileInputStream(file), file.getName());
			//out= sftpChannel.get(file.getAbsolutePath());
	} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
			sftpChannel.disconnect();
			session.disconnect();
	}
}
