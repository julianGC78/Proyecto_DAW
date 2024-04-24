package paixel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import paixel.servicesImpl.ServiceMatriculaImpl;

@RestController

public class MatriculaWS {
	@Autowired
	ServiceMatriculaImpl serviceMatriculaImpl;

}
