package io.choerodon.test.manager.app.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.http.ResponseEntity;

import io.choerodon.devops.api.dto.ApplicationRepDTO;
import io.choerodon.devops.api.dto.ApplicationVersionRepDTO;
import io.choerodon.devops.api.dto.DevopsApplicationDeployDTO;
import io.choerodon.devops.api.dto.ReplaceResult;
import io.choerodon.base.domain.PageRequest;
import io.choerodon.test.manager.api.vo.IssueInfosVO;
import io.choerodon.agile.api.vo.*;

/**
 * Created by 842767365@qq.com on 6/11/18.
 */
public interface TestCaseService {

    ResponseEntity<PageInfo<IssueListTestVO>> listIssueWithoutSub(Long projectId, SearchDTO searchDTO, PageRequest pageRequest, Long organizationId);

    ResponseEntity<PageInfo<IssueComponentDetailVO>> listIssueWithoutSubDetail(Long projectId, SearchDTO searchDTO, PageRequest pageRequest, Long organizationId);

    ResponseEntity<IssueDTO> queryIssue(Long projectId, Long issueId, Long organizationId);

    Map<Long, IssueInfosVO> getIssueInfoMap(Long projectId, SearchDTO searchDTO, PageRequest pageRequest, Long organizationId);

    <T> Map<Long, IssueInfosVO> getIssueInfoMapAndPopulatePageInfo(Long projectId, SearchDTO searchDTO, PageRequest pageRequest, Page page, Long organizationId);

    Map<Long, IssueInfosVO> getIssueInfoMap(Long projectId, SearchDTO searchDTO, boolean needDetail, Long organizationId);

    Map<Long, IssueInfosVO> getIssueInfoMap(Long projectId, Long[] issueIds, boolean needDetail, Long organizationId);

    Map<Long, IssueInfosVO> getIssueInfoMap(Long projectId, Long[] issueIds, PageRequest pageRequest, Long organizationId);

    List<IssueLinkDTO> getLinkIssueFromIssueToTest(Long projectId, List<Long> issueId);

    List<IssueLinkDTO> getLinkIssueFromTestToIssue(Long projectId, List<Long> issueId);

    List<IssueLinkDTO> listIssueLinkByIssueId(Long projectId, List<Long> issueId);

    Map<Long, ProductVersionDTO> getVersionInfo(Long projectId);

    ResponseEntity<PageInfo<ProductVersionPageDTO>> getTestCycleVersionInfo(Long projectId, Map<String, Object> searchParamMap);

    Long[] getVersionIds(Long projectId);

    ProjectDTO getProjectInfo(Long projectId);

    List<Long> queryIssueIdsByOptions(SearchDTO searchDTO, Long projectId);

    IssueDTO createTest(IssueCreateDTO issueCreateDTO, Long projectId, String applyType);

    List<IssueSearchDTO> batchIssueToVersion(Long projectId, Long versionId, List<Long> issueIds);

    List<Long> batchCloneIssue(Long projectId, Long versionId, Long[] issueIds);

    ResponseEntity batchIssueToVersionTest(Long projectId, Long versionId, List<Long> issueIds);

    ResponseEntity batchDeleteIssues(Long projectId, List<Long> issueIds);

    List<IssueInfoDTO> listByIssueIds(Long projectId, List<Long> issueIds);

    PageInfo<ComponentForListDTO> listByProjectId(Long projectId);

    List<IssueLabelDTO> listIssueLabel(Long projectId);

    LookupTypeWithValuesDTO queryLookupValueByCode(Long projectId, String typeCode);

    List<IssueStatusDTO> listStatusByProjectId(Long projectId);

    String getVersionValue(Long projectId, Long appVersionId);

    ApplicationRepDTO queryByAppId(Long projectId, Long applicationId);

    List<ApplicationVersionRepDTO> getAppversion(Long projectId, List<Long> appVersionId);

    ReplaceResult previewValues(Long projectId, ReplaceResult replaceResult, Long appVersionId);

    void deployTestApp(Long projectId, DevopsApplicationDeployDTO applicationDeployDTO);
}
