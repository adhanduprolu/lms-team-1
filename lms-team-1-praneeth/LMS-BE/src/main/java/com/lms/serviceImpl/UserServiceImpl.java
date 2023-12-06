package com.lms.serviceImpl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lms.dto.AllCourseUsersDto;
import com.lms.dto.UserCoursesDto;
import com.lms.dto.UserVerifyDto;
import com.lms.dto.VideoDto;
import com.lms.entity.CourseLink;
import com.lms.entity.CourseModules;
import com.lms.entity.CourseUsers;
import com.lms.entity.Courses;
import com.lms.entity.User;
import com.lms.exception.details.CustomException;
import com.lms.exception.details.EmailNotFoundException;
import com.lms.repository.CourseUsersRepo;
import com.lms.repository.CoursesRepo;
import com.lms.repository.OtpRepo;
import com.lms.repository.UserRepo;
import com.lms.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo lur;

	@Autowired
	private PasswordEncoder pe;

	@Autowired
	private OtpRepo or;

	@Autowired
	private CourseUsersRepo ucr;

	@Autowired
	private CoursesRepo cr;

//	@Autowired
//	private ModulesRepo mr;

	@Override
	public User saveLU(User lu) {

		User lu1 = new User();

		lu1.setName(lu.getName());
		lu1.setEmail(lu.getEmail());
		lu1.setPassword(pe.encode(lu.getPassword()));
		lu1.setRoles(lu.getRoles());

		if (getByemail(lu1)) {
			return null;
		} else {
			return lur.save(lu1);
		}
	}

	@Override
	public Boolean getByemail(User lu) {
		boolean findByName = lur.existsByemail(lu.getEmail());
		return findByName;
	}

	@Override
	public Optional<User> fingbyemail(String email) {

		Optional<User> findByemail;
		try {
			findByemail = lur.findByemail(email);
			return findByemail;
		} catch (Exception e) {
			throw new EmailNotFoundException("Email Not Found");
		}
	}

	@Override
	public List<User> getLU(long id) {
		return null;
	}

	@Override
	public User updateLU(User lu) {
		return null;
	}

	@Override
	public void deleteLU(long id) {
		return;
	}

	@Override
	public ResponseEntity<?> getby(User lu) {

		if (lur.findByemail(lu.getEmail()).isEmpty()) {
			throw new EmailNotFoundException("Email Not Found");
		} else {
			return new ResponseEntity<Object>(lur.findByemail(lu.getEmail()).get(), HttpStatus.OK);
		}
	}

	@Override
	public String saveImg(MultipartFile mp, String email) throws Exception {

		User op = lur.findByemail(email).orElseThrow(() -> new EmailNotFoundException("Email Not Found"));
		try {
			op.setImg(mp.getBytes());
			lur.save(op);
			return "Image File Uploaded Successfully :" + mp.getOriginalFilename().toLowerCase();
		} catch (IOException e) {
			throw new CustomException("Incorrect Image File");
		}

	}

	@Override
	public byte[] downloadImage(String email) throws IOException, DataFormatException {

		User img = lur.findByemail(email).orElseThrow(() -> new EmailNotFoundException("Email Not Found"));

		if (img.getImg() != null) {
			return img.getImg();
		} else {
			return null;
		}

	}

	@Override
	public User Luupdate(User lu) {

		User lu1;
		if (lu.getEmail() == null && lu.getImg() == null && lu.getName() == null && lu.getPassword() == null) {
			throw new CustomException("Empty Details Failed To Update:");

		} else {
			lu1 = lur.findByemail(lu.getEmail()).orElseThrow(() -> new EmailNotFoundException("Email Not Found"));
		}

		if (lu.getEmail() != null && !lu.getEmail().isEmpty()) {
			lu1.setEmail(lu.getEmail());
		}
		if (lu.getPassword() != null && !lu.getPassword().isEmpty()) {
			lu1.setPassword(pe.encode(lu.getPassword()));
		}
		if (lu.getName() != null && !lu.getName().isEmpty()) {
			lu1.setName(lu.getName());
		}
		if (lu.getImg() != null && lu.getImg().length != 0) {
			lu1.setImg(lu.getImg());
		}

		return lur.save(lu1);

	}

	@Override
	public boolean saveotp(UserVerifyDto uvt) {

		if (!or.save(uvt).equals(null)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean verifyAccount(String email, String otp) {

		Optional<UserVerifyDto> findByemail;
		try {
			findByemail = or.findByemail(email);

			if (findByemail.get().getOtp().equals(otp) && Duration
					.between(findByemail.get().getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new EmailNotFoundException("Email Not Found");
		}
	}

	@Override
	public boolean resetPassword(String password, String verifypassword, long id) {

		User findById = lur.findById(id).orElseThrow(() -> new CustomException("Invalid Id"));
		if (password.equals(verifypassword)) {
			findById.setPassword(pe.encode(verifypassword));
			return true;
		}
		return false;
	}

	@Override
	public boolean saveCourseUser(CourseUsers uc) {

		CourseUsers save = ucr.save(uc);
		if (save == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean saveCourses(Courses cc) {

		cc.setCoursecreatedate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss")));

		Courses save = cr.save(cc);

		if (save == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public boolean accessCouresToUser(String name, String cname, String trainername) {

		boolean userExists = ucr.existsByusername(name);
		boolean courseExists = cr.existsBycoursename(cname);

		if (userExists && courseExists) {

			CourseUsers fun = ucr.findByuseremail(name);
			List<Courses> fcn = cr.findBycoursename(cname);

			Optional<Courses> courseOptional = fcn.stream()
					.filter(course -> course.getCoursetrainer().equals(trainername)).findFirst();

			if (!fun.getCourseslist().containsAll(fcn)) {
				fun.getCourseslist().add(courseOptional.get());
				ucr.save(fun);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public UserCoursesDto getCourseUsers(String name) {

		try {
			CourseUsers fun = ucr.findByuseremail(name);

			UserCoursesDto ucd = UserCoursesDto.builder().username(fun.getUsername()).useremail(fun.getUseremail())
					.courseslist(fun.getCourseslist()).build();

			return ucd;
		} catch (Exception e) {
			throw new CustomException("No User" + name);
		}

	}

	@Override
	public List<AllCourseUsersDto> getCourses(String name, String fname) {

		try {
			List<Courses> findByusername = cr.findBycoursename(name);

			List<AllCourseUsersDto> collect = findByusername.stream()
					.filter(fil -> fil.getCoursetrainer().equals(fname))
					.map(c -> new AllCourseUsersDto(c.getCoursename(), c.getCoursetrainer(), c.getCourseusers()))
					.collect(Collectors.toList());
			return collect;
		} catch (Exception e) {
			throw new CustomException("No User " + name);
		}

	}

	@Override
	public String addVideoLink(VideoDto vd) {

		LinkedHashSet<String> videolink = vd.getVideolink();

		List<String> videoname = vd.getVideoname();
		List<String> linklist = new ArrayList<>(videolink);

		if (videoname.size() < videolink.size() || videolink.size() > videoname.size()) {
			throw new CustomException("Video Title or Video Link Is Missing");
		} else {
			LinkedHashMap<String, String> linkedmap = new LinkedHashMap<>();

			Iterator<String> nameIterator = videoname.iterator();
			Iterator<String> linkIterator = linklist.iterator();

			while (nameIterator.hasNext() && linkIterator.hasNext()) {
				String name = nameIterator.next();
				String link = linkIterator.next();
				linkedmap.put(name, link);
			}
			// find the details from db using cname, trainername
			List<Courses> fcn = cr.findBycoursenameAndcoursetrainer(vd.getCname(), vd.getTname());

			CourseLink cl = CourseLink.builder().link(linklist).videoname(vd.getVideoname()).build();

			List<CourseLink> cl1 = new ArrayList<>();
			cl1.add(cl);

			// converting the details into cm object
			CourseModules cm = CourseModules.builder().modulenum(vd.getModulenum())
					.videoinserttime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy "))).clinks(cl1)
					.build();

			// if fcn contains
			if (fcn.size() > 0) {

				// by using tname gettiing the course object
				Courses courses = fcn.stream().filter(course -> course.getCoursetrainer().equals(vd.getTname()))
						.findFirst().get();

				// getting the coursemodules from courses
				List<CourseModules> existingModules = courses.getCoursemodule();

				// if courses are already exist the it goes inside else outside
				if (existingModules.size() > 0) {

					// check the modulenum from db and from client if both are same then return the
					// coursemodule list
					Optional<CourseModules> em = existingModules.stream()
							.filter(module -> module.getModulenum() == vd.getModulenum()).findFirst();

					// add the videolink to set of link if the module if present or else add the
					// builder to existingmocules list

					if (em.isPresent()) {

						CourseModules cm1 = em.get();
//
						List<CourseLink> clinks = cm1.getClinks();
//
//						log.info("" + clinks);
//
//						clinks.addAll(cl1);
//
//						log.info("" + clinks);
//						cm1.setClinks(clinks);
//						log.info("" + cm1);

						log.info("" + clinks);

						if (clinks.size() > 0) {
							for (CourseLink existingCl : clinks) {
								log.info("" + existingCl);
								existingCl.getLink().addAll(cl.getLink());
								existingCl.getVideoname().addAll(cl.getVideoname());
							}
						} else {

							clinks.addAll(cl1);

						}

					} else {

						existingModules.add(cm);
						// log.info("" +existingModules);
					}

				} else {
					existingModules.add(cm);
				}
				// set the course object with new setcoursemodule
				courses.setCoursemodule(existingModules);
				cr.save(courses);

				return "Video Saved";
			} else {
				return "Video Not Saved";
			}
		}

	}

	@Override
	public List<CourseModules> getVideoLink(String name, String cname, String tname) {

		try {
			CourseUsers courseUsers = ucr.findByuseremail(name);

			List<CourseModules> collect = courseUsers.getCourseslist().stream()
					.filter(fil -> fil.getCoursename().equals(cname) && fil.getCoursetrainer().equals(tname))
					.flatMap(courses -> courses.getCoursemodule().stream()).collect(Collectors.toList());

			if (collect.size() > 0) {
				return collect;
			} else {
				throw new CustomException("No Videos Available");
			}
		} catch (Exception e) {
			throw new CustomException("Name or Cname or Trainername Not Found");
		}

	}

}
