package com.example.plugins.tutorial.jira.workflow;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.exception.DataAccessException;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.status.Status;
import com.atlassian.jira.mock.component.MockComponentWorker;
import com.atlassian.jira.util.collect.MapBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParentIssueBlockingConditionTest  extends AbstractWorkflowTest
{
    private ParentIssueBlockingCondition condition;


    @Before
    public void setup()
    {
        setupMocks();
        condition = new ParentIssueBlockingCondition();
        transientVars = MapBuilder.build("originalissueobject", mockSubTaskIssue);
        args = MapBuilder.build("statuses", "1,2,3");
        ComponentAccessor.initialiseWorker(new MockComponentWorker().addMock(IssueManager.class, mockIssueManager));
    }

    @Test
    public void testPassesCondition() throws Exception
    {

        when(mockStatus.getId()).thenReturn("3");
        assertTrue("condition should pass",condition.passesCondition(transientVars, args, null));
    }

    @Test
    public void testFailsCondition() throws Exception
    {
        when(mockStatus.getId()).thenReturn("4");
        assertFalse("condition should fail",condition.passesCondition(transientVars, args, null));
    }


}
