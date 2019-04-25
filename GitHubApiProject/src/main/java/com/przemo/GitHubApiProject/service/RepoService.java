package com.przemo.GitHubApiProject.service;

import java.io.IOException;


import com.przemo.GitHubApiProject.entity.Repo;

public interface RepoService 
{
	Repo getLatestModifiedRepository(String company) throws IOException;
}
