package com.sree.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	/**
	 * Basic Versioning  --> URI Versioning
	 */
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Basic Versioning --> URI Versioning
	 */
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob"," Charlie"));
	}
	
	/**
	 * Advance Versioning using request param --> Request Parameter Versioning
	 * 
	 * http://localhost:8080/person/param?version=1
	 */
	@GetMapping(value="person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Advance Versioning using request param --> Request Parameter Versioning
	 * 
	 * http://localhost:8080/person/param?version=2
	 */
	@GetMapping(value="person/param", params="version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Bob"," Charlie"));
	}
	
	/**
	 * Advance Versioning using header Param --> Header versioning
	 * 
	 * http://localhost:8080/person/header
	 * Pass in the header --> X-API-VERSION : 1
	 */
	@GetMapping(value="person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Advance Versioning using header parameter  --> Header versioning
	 * 
	 * http://localhost:8080/person/header
	 * Pass in the header --> X-API-VERSION : 2
	 */
	@GetMapping(value="person/header", headers="X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Bob"," Charlie"));
	}
	
	/**
	 * Advance Versioning using produces, also called Content Negotiation or Accept versioning or MIME Type Versioning
	 * 
	 * http://localhost:8080/person/produces
	 * Pass in the header --> Accept : "application/vnd.company.app-v1+json", instead of Accept : "application/json"
	 */
	@GetMapping(value="person/produces", produces = "application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Bob Charlie");
	}
	/**
	 * Advance Versioning using produces, also called Content Negotiation or Accept versioning or MIME Type Versioning
	 * 
	 *  http://localhost:8080/person/produces
	 * Pass in the header --> Accept : "application/vnd.company.app-v2+json", instead of Accept : "application/json"
	 */
	@GetMapping(value="person/produces", produces = "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Bob"," Charlie"));
	}
	
	
}