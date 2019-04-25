package com.przemo.GitHubApiProject.service;

import java.io.IOException;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.stereotype.Service;

import com.przemo.GitHubApiProject.entity.Repo;
import com.przemo.GitHubApiProject.exception.LackOfRepositoriesExcpetion;

@Service
public class RepoServiceImpl implements RepoService
{
	private RepositoryService repoService = new RepositoryService();
	
	public Repo getLatestModifiedRepository(String company) throws IOException {
		
		//retrieve all repositories from github
		RepositoryService service = repoService;
		List<Repository> repositories = service.getRepositories(company);
		
		// if there is lack of Repositories throw Exception
		if(repositories.isEmpty()) throw new LackOfRepositoriesExcpetion(company);
		
		//sort list of repositories by latest update date
		repositories.sort((r1,r2) ->r1.getUpdatedAt().compareTo(r2.getUpdatedAt()));
		
		//create Repo to return (with latest update date)
		Repo repo = new Repo();
		repo.setRepo_name(repositories.get(repositories.size()-1).getName());
		return repo;
	}
	
}