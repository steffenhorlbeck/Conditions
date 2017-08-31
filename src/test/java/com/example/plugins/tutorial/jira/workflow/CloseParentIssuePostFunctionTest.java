package com.example.plugins.tutorial.jira.workflow;

import com.atlassian.jira.bc.project.component.ProjectComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.ConstantsManager;
import com.atlassian.jira.config.SubTaskManager;
import com.atlassian.jira.exception.DataAccessException;
import com.atlassian.jira.issue.*;
import com.atlassian.jira.issue.label.LabelManager;
import com.atlassian.jira.issue.security.IssueSecurityLevelManager;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.mock.component.MockComponentWorker;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.user.util.UserManager;
import com.atlassian.jira.util.collect.MapBuilder;
import com.atlassian.jira.workflow.WorkflowManager;
import com.opensymphony.workflow.spi.WorkflowEntry;
import org.ofbiz.core.entity.GenericValue;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class CloseParentIssuePostFunctionTest extends AbstractWorkflowTest
{


    protected CloseParentIssuePostFunction function;

    private ConstantsManager mockConstantsManager;
    private WorkflowManager mockWorkflowManager;
    private SubTaskManager mockSubTaskManager;
    private JiraAuthenticationContext mockAuthenticationContext;

    private Map transientVars, args;


    @Before
    public void setup() {

        setupMocks();
        mockConstantsManager = mock(ConstantsManager.class);
        mockWorkflowManager = mock(WorkflowManager.class);
        mockSubTaskManager = mock(SubTaskManager.class);
        mockAuthenticationContext = mock(JiraAuthenticationContext.class);
        when(mockStatus.getId()).thenReturn("1");
        when(mockSubTaskManager.getSubTaskObjects(mockParentIssue)).thenReturn(Collections.<Issue>emptyList());
        transientVars = MapBuilder.newBuilder().add("originalissueobject", mockSubTaskIssue).add("entry",mock(WorkflowEntry.class)).toMap();

        args = MapBuilder.build("statuses", "1,2,3");
        ComponentAccessor.initialiseWorker(new MockComponentWorker().addMock(IssueManager.class, mockIssueManager));
        transientVars = Collections.emptyMap();
        args = Collections.emptyMap();
        ComponentAccessor.initialiseWorker(new MockComponentWorker().addMock(IssueManager.class, mockIssueManager));
        function = new CloseParentIssuePostFunction(mockConstantsManager, mockWorkflowManager, mockSubTaskManager, mockAuthenticationContext){

            @Override
            protected MutableIssue getIssue(Map transientVars) throws DataAccessException {
                return mockSubTaskIssue;
            }
        };

    }

    @Test
    public void testNullMessage() throws Exception
    {


        //function.execute(transientVars,args,null);


    }

    @Test
    public void testEmptyMessage() throws Exception
    {


        //function.execute(transientVars,args,null);

       // assertEquals("message should be empty","",issue.getDescription());
    }

    @Test
    public void testValidMessage() throws Exception
    {


        //function.execute(transientVars,args,null);

        //assertEquals("message not found",MESSAGE,issue.getDescription());
    }
}
