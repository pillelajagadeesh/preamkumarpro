package appraamlabs.service.gcmNotification;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import appraamlabs.controllers.BeaconController;
import appraamlabs.utils.constants.MessageConstants;

@Service
public class GCMNotificationService {
	
	@Autowired
	private Environment env;
	
	private static Logger logger = LoggerFactory.getLogger(GCMNotificationService.class);

	public boolean sendGCMNotification(String regId, String message){
		
		logger.debug("Request to send gcm push notification");
		
		String GOOGLE_SERVER_KEY = env.getProperty(MessageConstants.GCM_KEY);
		String MESSAGE_KEY = "message";
     	Result result = null;

	// GCM RedgId of Android device to send push notification
		try {
			//regId = "eHVomRJ7XNs:APA91bEsOoNqHxhJ_EppKJCy6ShERVJeOA5jWvb5bX_oUFTc7UEp1wNb56S1HYdteftk0PNCH0XZiq4SIqfF9H1T-NtiiWymyiH-8ItGzRz5xIjMWemp6uJtXEsRaW3NytAokCSBudPs";
			Sender sender = new Sender(GOOGLE_SERVER_KEY);
/*			Message message = new Message.Builder().timeToLive(30)
					.delayWhileIdle(true).addData(MESSAGE_KEY, message).build();*/
			Message messageObj = new Message.Builder().addData(MESSAGE_KEY,message).build();
			result = sender.send(messageObj, regId, 1);
			
			if (StringUtils.isEmpty(result.getErrorCodeName())) {
                logger.debug("GCM Notification is sent successfully with result {}", result.toString());
                return true;
            }

            logger.error("Error occurred while sending push notification with error code {}", result.getErrorCodeName());
            
		}catch(InvalidRequestException  ie){
			logger.error("Notification error with message {} ", ie.getMessage());
		} catch (IOException ioe) {
			logger.error("Notification error with message {} ", ioe.getMessage());
		}
		
		return false;
	}
}
