package br.com.tpartner.controller.profile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.tpartner.controller.AbstractController;
import br.com.tpartner.exceptions.ValidationException;
import br.com.tpartner.model.infrastructure.User;
import br.com.tpartner.model.profile.UserProfile;
import br.com.tpartner.model.log.UserLog;
import br.com.tpartner.repository.interfaces.profile.UserProfileRepositoryInterface;
import br.com.tpartner.repository.interfaces.log.UserLogRepositoryInterface;

@RestController
@Transactional
@RequestMapping(value = "/user_profile")
public class UserProfileController extends AbstractController<UserProfile, Integer>{
	
	@Autowired
	@Qualifier("userProfileRepository")
	private UserProfileRepositoryInterface repository;

        @Override
	protected UserProfileRepositoryInterface getRepository() {
		return repository;
	}
	
	@RequestMapping(value = "/user/{id}")
	@ResponseBody
	public UserProfile findUserProfileById(@PathVariable Integer id){
		return getRepository().findUserProfileById(id);
	}
	
	@RequestMapping(value = "/user/name/{name}")
	@ResponseBody
	public List<UserProfile> findUserProfileByName(@PathVariable String name){
		return getRepository().findUserProfileByName(name);
	}
	
	@Autowired
	@Qualifier("userLogRepository")
	private UserLogRepositoryInterface logRepository;
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
        @Override
	public void delete(@PathVariable Integer id) {
		UserProfile deletedUserProfile = getRepository().findById(id);
		
		User user = deletedUserProfile.getUser();
		
		UserLog userLog = new UserLog();
		userLog.setUser(user);
		
		userLog.setActivity("Remove profile");
		
		userLog.setTitle(user.getUserProfile().getName()+" said goodbye to iFace");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);
		
		getRepository().delete(deletedUserProfile);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
        @Override
	public UserProfile save(@RequestBody @Valid UserProfile newUserProfile, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserProfile.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Create profile");
		userLog.setTitle(newUserProfile.getName()+" starts using iFace");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().save(newUserProfile);
	}
	
	@RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
        @Override
	public UserProfile update(@RequestBody @Valid UserProfile newUserProfile, BindingResult result,
			HttpServletResponse response) throws JsonParseException,
			JsonMappingException, IOException {
		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		UserLog userLog = new UserLog();		
		User user = newUserProfile.getUser();
		userLog.setUser(user);
		
		userLog.setActivity("Profile modification");
		
		userLog.setTitle(newUserProfile.getName()+"modified profile");
		
		userLog.setTimestamp(new Date());
		
		logRepository.save(userLog);

		return getRepository().update(newUserProfile);
	}
	
	@RequestMapping(value="/upload_picture/{id}", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload( 
            @RequestParam("file") MultipartFile file, @PathVariable Integer id){
    	URL resource = getClass().getResource("/");
        if (!file.isEmpty()) {
            try {
            	String filename = "profile"+id+"-"+(new Date()).getTime();
            	String path = URLDecoder.decode(resource.getPath(),"UTF-8");
                byte[] bytes = file.getBytes();
                try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path+"../../app/profile-images/"+filename)))) {
                    stream.write(bytes);
                }
                return "/app/profile-images/"+filename;
            } catch (IOException e) {
                return "FAIL ON UPLOAD FILE: " + e.getMessage();
            }
        } else {
            return "EMPTY FILE ON UPLOAD";
        }
    }
	
	@RequestMapping(value="/download_picture", method=RequestMethod.POST)
    public @ResponseBody String handleUrlDownload( @RequestParam("id") Integer id, @RequestParam("url") String url){
    	URL resource = getClass().getResource("/");
        if (!url.isEmpty()) {
            try {
            	String filename = "profile"+id+"-"+(new Date()).getTime();
            	String path = URLDecoder.decode(resource.getPath(),"UTF-8");
                File f = new File(path+"../../app/profile-images/"+filename);
                FileUtils.copyURLToFile(new URL(URLDecoder.decode(url, "UTF-8")), f);
                
                return "/app/profile-images/"+filename;
            } catch (IOException e) {
                return "FAIL ON UPLOAD FILE: " + e.getMessage();
            }
        } else {
            return "EMPTY FILE ON UPLOAD";
        }
    }
}
