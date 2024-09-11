package com.gym.mail.management.service;

import com.response.gym.response.MMTResponseCreator;

public interface LinkAccepterService {

    MMTResponseCreator acceptConfirmation(String confirmation);
}
