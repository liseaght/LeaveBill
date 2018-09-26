package xin.snowsteps.utils;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import xin.snowsteps.common.Const;

public class ManageTaskHandler implements TaskListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setAssignee(Const.MANAGER);
	}

}
