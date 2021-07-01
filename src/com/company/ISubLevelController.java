package com.company;

public interface ISubLevelController {
    void Initializate(ISubLevelViewer viewer, ISubLevelModel model);
    void React(int direction);
}
