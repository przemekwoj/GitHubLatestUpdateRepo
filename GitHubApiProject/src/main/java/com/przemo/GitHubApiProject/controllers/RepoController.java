package com.przemo.GitHubApiProject.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.przemo.GitHubApiProject.entity.Repo;
import com.przemo.GitHubApiProject.service.RepoService;

@RestController
public class RepoController
{
	
	@Autowired
	RepoService repoService;
	
	private String company = "allegro";
	
	@GetMapping("/lastModified")
	public Repo lastModifiedRepository() throws IOException 
	{
		return repoService.getLatestModifiedRepository(company);
	}
}
