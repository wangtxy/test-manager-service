package io.choerodon.test.manager.app.service.impl;

import io.choerodon.core.exception.CommonException;
import io.choerodon.test.manager.api.dto.TestCaseStepDTO;
import io.choerodon.test.manager.app.service.TestCaseStepService;
import io.choerodon.test.manager.domain.service.ITestStatusService;
import io.choerodon.test.manager.domain.test.manager.entity.TestCaseStepE;
import io.choerodon.test.manager.domain.service.ITestCaseStepService;
import io.choerodon.core.convertor.ConvertHelper;
import io.choerodon.test.manager.domain.test.manager.entity.TestCycleCaseE;
import io.choerodon.test.manager.domain.test.manager.entity.TestCycleCaseStepE;
import io.choerodon.test.manager.domain.test.manager.entity.TestStatusE;
import io.choerodon.test.manager.domain.test.manager.factory.TestCycleCaseEFactory;
import io.choerodon.test.manager.domain.test.manager.factory.TestCycleCaseStepEFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 842767365@qq.com on 6/11/18.
 */
@Component
public class TestCaseStepServiceImpl implements TestCaseStepService {
	@Autowired
	ITestCaseStepService iTestCaseStepService;

	@Autowired
	ITestStatusService iTestStatusService;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void removeStep(TestCaseStepDTO testCaseStepDTO) {
		iTestCaseStepService.removeStep(ConvertHelper.convert(testCaseStepDTO, TestCaseStepE.class));
	}


	@Override
	public List<TestCaseStepDTO> query(TestCaseStepDTO testCaseStepDTO) {
		return ConvertHelper.convertList(iTestCaseStepService.query(ConvertHelper.convert(testCaseStepDTO, TestCaseStepE.class)), TestCaseStepDTO.class);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public TestCaseStepDTO changeStep(TestCaseStepDTO testCaseStepDTO, Long projectId) {
		Assert.notNull(testCaseStepDTO,"error.case.change.step.param.not.null");
		TestCaseStepE testCaseStepE = ConvertHelper.convert(testCaseStepDTO, TestCaseStepE.class);
		if (testCaseStepE.getStepId() == null) {
			testCaseStepE = testCaseStepE.createOneStep();
		} else {
			testCaseStepE = testCaseStepE.changeOneStep();
		}
		return ConvertHelper.convert(testCaseStepE, TestCaseStepDTO.class);
	}

	@Transactional
	@Override
	public TestCaseStepDTO clone(TestCaseStepDTO testCaseStepDTO, Long projectId) {
		TestCaseStepE testCaseStepE = ConvertHelper.convert(testCaseStepDTO, TestCaseStepE.class);
		List<TestCaseStepE> steps = testCaseStepE.querySelf();
		if (steps.size() != 1) {
			throw new CommonException("error.clone.case.step");
		}
		testCaseStepE = steps.get(0);
		testCaseStepE.setStepId(null);
		testCaseStepE.setLastRank(testCaseStepE.getLastedStepRank());
		testCaseStepE.setObjectVersionNumber(null);
		return changeStep(ConvertHelper.convert(testCaseStepE, TestCaseStepDTO.class), projectId);

	}

}
