package com.company.innoagri.screen.todo;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ToDo;

@UiController("ToDo.browse")
@UiDescriptor("to-do-browse.xml")
@LookupComponent("toDoesTable")
public class ToDoBrowse extends StandardLookup<ToDo> {
}