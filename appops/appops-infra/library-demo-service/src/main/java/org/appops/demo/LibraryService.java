package org.appops.demo;

import org.appops.infra.core.annotations.Service;

@Service
public interface LibraryService {

	public String getMyBooks(String name);
}
