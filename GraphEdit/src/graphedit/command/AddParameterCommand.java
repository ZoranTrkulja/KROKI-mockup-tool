package graphedit.command;

import graphedit.model.components.GraphElement;
import graphedit.model.components.LinkableElement;
import graphedit.model.components.Method;
import graphedit.model.components.Parameter;
import graphedit.model.components.shortcuts.Shortcut;
import graphedit.model.elements.ClassElement;
import graphedit.view.GraphEditView;

public class AddParameterCommand extends Command {

	private GraphElement element;
	private Method method;
	private Parameter parameter;
	private ClassElement classElement;
	private int index;

	public AddParameterCommand(GraphEditView view, GraphElement element, Method method, Parameter parameter) {
		this.view = view;
		this.method = method;
		this.parameter = parameter;
		
		if (element instanceof Shortcut)
			this.element = ((Shortcut) element).shortcutTo();
		else
			this.element = element;
		
		classElement = (ClassElement) this.element.getRepresentedElement();
		index = method.getParameters().size();
	}

	@Override
	public void execute() {
		classElement.addParameter(method, parameter);
		method.addParameter(index,parameter);
		
		updatePainters((LinkableElement) element);
		
		view.getModel().fireUpdates();
	}

	@Override
	public void undo() {
		classElement.removeParameter(method, parameter);
		method.removeParameter(parameter);
		
		updatePainters((LinkableElement) element);
		
		view.getModel().fireUpdates();
	}

}
