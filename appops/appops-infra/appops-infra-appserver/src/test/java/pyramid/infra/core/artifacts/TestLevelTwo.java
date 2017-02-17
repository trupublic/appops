package pyramid.infra.core.artifacts;

import pyramid.infra.core.annotations.Service;

@Service(name = "secondLevelService" , parent = TestLevelOne.class)
public interface TestLevelTwo {

}
