package com.example.proyectoiconosfx.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IconList {

	@JsonProperty("ResponseAll")
	public List<Icon> responseAll;


	@Override
 	public String toString(){
		return 
			"ResponseAll{" + 
			"responseAll = '" + responseAll + '\'' + 
			"}";
		}
}