package org.appops.infra.core.artifacts;

import org.appops.infra.core.annotations.Service;

@Service(name = "secondLevelService" , parent = TestLevelOne.class)
public interface TestLevelTwo {

}
