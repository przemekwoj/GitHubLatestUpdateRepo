package com.przemo.GitHubApiProject.exception;

public class LackOfRepositoriesExcpetion extends RuntimeException
{	
		private String company;


	    public LackOfRepositoriesExcpetion(String company) {
	        this.company = company;
	    }
	    
	    @Override
	    public String getMessage() {
	    	return "Lack of repositories on "+company+ " github";
	    }

}
