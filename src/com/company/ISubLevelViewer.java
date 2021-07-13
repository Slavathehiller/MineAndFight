package com.company;

import javax.swing.*;

public interface ISubLevelViewer {
    void DrawLocation();
    void InitializeControl();
    void Log(String message);
    void PlayerDeadMessage();
    void EndLevel();
    void ShowMessage_NotEnoughStamina();
    void ShowMessage_CustomMessage(String message);


}
