package org.elevenfifty.java301.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.elevenfifty.java301.beans.Ingredients;
import org.elevenfifty.java301.beans.User;
import org.elevenfifty.java301.beans.UserImage;
import org.elevenfifty.java301.repository.UserImageRepository;
import org.elevenfifty.java301.repository.UserRepository;
import org.elevenfifty.java301.repository.UserPropertyRepository;
import org.elevenfifty.java301.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexController {
	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserImageRepository userImageRepo;

	// @Autowired
	// private UserpropertiesRepository userPropertiesRepo;
	//
	// @Autowired
	// private UserrolesRepository userRolesRepo;

	@GetMapping("")
	// @RequestMapping(path="", method=RequestMethod.GET)
	public String index(Model model) {
		return "index";

	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping(path = { "/home", "/", " " })
	public String home(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "home";
	}

	@GetMapping("/user/{id}")
	public String user(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("id", id);
		User u = userRepo.findOne(id);
		model.addAttribute("user", u);
		return "user_edit";
	}

	// TODO Fix these
	@PostMapping("/user/{id}/edit")
	public String userEditSave(@PathVariable(name = "id") long id, @ModelAttribute @Valid User user,
			BindingResult result, Model model, @RequestParam("file") MultipartFile file,
			@RequestParam(name = "removeImage", defaultValue = "false", required = false) boolean removeImage) {
		// //TODO Why is the Request Parameter, not highlighting? I clicked on
		// it
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "user_edit";
		} else {

			if (removeImage) {
				UserImage image = userImageRepo.findByUserId(id);

				if (image != null) {
					userImageRepo.delete(image);
					log.info("Image Removed" + id);
				}
			} else {

				if (file != null && !file.isEmpty()) {

					try {

						// Check curly brackets
						UserImage image = userImageRepo.findByUserId(id);

						if (image == null) {
							image = new UserImage();
							image.setUserId(id);
						}
						image.setContentType(file.getContentType());
						image.setImage(file.getBytes());
						userImageRepo.save(image);
					} catch (IOException e) {
						log.error("Failed to upload file", e);
					}
				}

			}
			userRepo.save(user);
			// //return "user_edit";
			return "redirect:/user/" + user.getId();
		}
	}

	@GetMapping("/user/{id}/edit")
	public String userEdit(Model model, @PathVariable(name = "id") long id) {
		model.addAttribute("id", id);
		User u = userRepo.findOne(id);
		// u.setActive(true);
		// u.setFirstName("James");
		// u.setLastName("Keim");
		// u.setId(1);
		// u.setPhoneNumber("317.867.5309");
		model.addAttribute("user", u);
		return "user_edit";
	}

}
