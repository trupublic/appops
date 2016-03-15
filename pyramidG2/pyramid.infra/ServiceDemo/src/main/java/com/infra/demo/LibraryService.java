package com.infra.demo;

import pyramid.infra.core.annotations.Service;

@Service
public interface LibraryService {

	public String getMyBooks(String name);
}
