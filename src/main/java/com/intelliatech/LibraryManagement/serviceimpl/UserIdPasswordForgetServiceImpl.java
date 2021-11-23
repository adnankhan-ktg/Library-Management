package com.intelliatech.LibraryManagement.serviceimpl;
import com.intelliatech.LibraryManagement.constants.Constants;
import com.intelliatech.LibraryManagement.controller.UserIdPasswordForgetController;
import com.intelliatech.LibraryManagement.dto.MailRequestDto;
import com.intelliatech.LibraryManagement.dto.SendOtpMobileDto;
import com.intelliatech.LibraryManagement.dto.UserIdPasswordForgetDto;
import com.intelliatech.LibraryManagement.exception.BusinessException;
import com.intelliatech.LibraryManagement.exception.ResponseMessage;
import com.intelliatech.LibraryManagement.model.User;
import com.intelliatech.LibraryManagement.repository.UserRepository;
import com.intelliatech.LibraryManagement.service.UserIdPasswordForgetService;
import com.intelliatech.LibraryManagement.service.helper.MailService;
import com.intelliatech.LibraryManagement.service.helper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserIdPasswordForgetServiceImpl implements UserIdPasswordForgetService {

    private static final Logger log = LoggerFactory.getLogger(UserIdPasswordForgetServiceImpl.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OtpService otpService;
    @Autowired
    private MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TwilioService twilioService;


    @Override
    public ResponseMessage getPasswordForgetOtp(UserIdPasswordForgetDto userIdPasswordForgetDto) throws BusinessException {
        log.info("Inside UserIdPasswordForgetServiceImpl in getPasswordForgetOtp()");
        //First Find the User
        //Database Call
        User user = this.userRepository.findByUsernameOrEmailOrMobileNumber(userIdPasswordForgetDto.getUserId(), userIdPasswordForgetDto.getUserId(), userIdPasswordForgetDto.getUserId());

        //Check User Exists or not
        if(user != null)
        {
            //Generate OTP with the Given Username
            int GeneratedOtp = otpService.generateOTP(userIdPasswordForgetDto.getUserId());
            //Change Otp Integer to String
            String GeneratedOtpString = Integer.toString(GeneratedOtp);
            //Create MailRequestDto for sending the mail
            MailRequestDto mailRequestDto = new MailRequestDto(user.getEmail(),"Your Library Management UserId Password Forget OTP is : "+GeneratedOtpString,"OTP for UserId Password Forget");
            //Send the mail
            mailService.sendMail(mailRequestDto);
            //Send otp to the user mobile number
            twilioService.otpSendToMobile(new SendOtpMobileDto(user.getMobileNumber(),GeneratedOtp));

            log.info("Leaving UserIdPasswordForgetServiceImpl in getPasswordForgetOtp()");
            return new ResponseMessage("OTP has been sent to your mobile number and email",200);


        }else{
            log.info("User Not found");
            log.info("Leaving UserIdPasswordForgetServiceImpl in getPasswordForgetOtp()");
             throw new BusinessException(404, Constants.USER_NOT_FOUND);
//            return new ResponseMessage("UserId not found",404);
        }




    }

    @Override
    public ResponseMessage validateOtp(UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception {
        log.info("Inside UseridPasswordForgetServiceImpl in validateOtp()");
        User user = this.userRepository.findByUsernameOrEmailOrMobileNumber(userIdPasswordForgetDto.getUserId(), userIdPasswordForgetDto.getUserId(), userIdPasswordForgetDto.getUserId());
        if(user != null)
        {
            int serverOtp = otpService.getOtp(userIdPasswordForgetDto.getUserId());
            int clientOtp = Integer.parseInt(userIdPasswordForgetDto.getOtp());
            if(serverOtp == clientOtp)
            {
                otpService.clearOTP(userIdPasswordForgetDto.getUserId());
                return new ResponseMessage(user.getDateOfBirth()+user.getGender(),200);
            }
            return new ResponseMessage(Constants.OTP_NOT_MATCH,406);

        }else{
            throw new BusinessException(404,Constants.USER_NOT_FOUND);
        }

    }

    @Override
    public ResponseMessage updatePassword(UserIdPasswordForgetDto userIdPasswordForgetDto) throws Exception {
        log.info("Inside UseridPasswordForgetServiceImpl in updatePassword");
        User user = this.userRepository.findByUsernameOrEmailOrMobileNumber(userIdPasswordForgetDto.getUserId(), userIdPasswordForgetDto.getUserId(), userIdPasswordForgetDto.getUserId());
        if(user != null)
        {
            if(userIdPasswordForgetDto.getKey().equals(user.getDateOfBirth()+user.getGender()))
            {
                user.setPassword(passwordEncoder.encode(userIdPasswordForgetDto.getPassword()));
                User user1 = this.userRepository.save(user);
                if(user1 != null){
                    log.info("Leaving UseridPasswordForgetServiceImpl in updatePassword");
                    return new ResponseMessage(Constants.PASSWORD_SUCCESSFULLY_MODIFIED,200);
                }else{
                    log.info("Leaving UseridPasswordForgetServiceImpl in updatePassword");
                    throw  new BusinessException(304,Constants.PASSWORD_NOT_MODIFIED);
                }


            }else{
                throw new BusinessException(401,Constants.KEY_NOT_MATCH);
            }

        }else{
            throw new BusinessException(404,Constants.USER_NOT_FOUND);
        }



    }
}
