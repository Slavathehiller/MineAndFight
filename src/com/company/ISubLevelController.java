package com.company;

import java.lang.reflect.InvocationTargetException;

public interface ISubLevelController {
    void Initializate(ISubLevelViewer viewer, ISubLevelModel model);
    void React(int direction) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException;
}
