package com.example.proyectoiconosfx.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Icon {

	@JsonProperty("htmlCode")
	public List<String> htmlCode;

	@JsonProperty("name")
	public String name;

	@JsonProperty("unicode")
	public List<String> unicode;

	@JsonProperty("category")
	public String category;

	@JsonProperty("group")
	public String group;



	public String getIcon() {
		return unicode.get(0);
	}

	public String getName(){
		return name;
	}

	public List<String> getUnicode(){

		return unicode;
	}

	public List<String>	getHtmlCode(){
		return htmlCode;}

	public String getCategory() {

		return category;
	}

	public String getGroup(){return group;}

	public void setUnicode(List<String> unicode) {
		this.unicode = unicode;
	}

	@Override
	public String toString() {
		return "Response{" +
				"htmlCode=" + htmlCode +
				", name='" + name + '\'' +
				", unicode=" + unicode +
				", category='" + category + '\'' +
				", group='" + group + '\'' +
				'}';
	}
}