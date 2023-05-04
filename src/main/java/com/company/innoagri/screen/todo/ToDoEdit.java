package com.company.innoagri.screen.todo;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ToDo;

@UiController("ToDo.edit")
@UiDescriptor("to-do-edit.xml")
@EditedEntityContainer("toDoDc")
public class ToDoEdit extends StandardEditor<ToDo> {
}