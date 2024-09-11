package com.gym.mail.management.factory;

import com.gym.mail.generator.model.EmailType;
import com.gym.mail.management.service.MailConfirmationProcessor;
import com.gym.mail.management.service.impl.MailConfirmationProcessorForAdvertisementImpl;
import com.gym.mail.management.service.impl.MailConfirmationProcessorForPasswordChangeImpl;
import com.gym.mail.management.service.impl.MailConfirmationProcessorForRegistrationImpl;
import org.springframework.stereotype.Component;

@Component
public class ActionByTypeResolver {

    private final ActionResolverFactory actionResolverFactory;

    public ActionByTypeResolver(ActionResolverFactory actionResolverFactory) {
        this.actionResolverFactory = actionResolverFactory;
    }

    public MailConfirmationProcessor resolveActionClass(EmailType type) {
        return switch (type) {
            case REGISTRATION -> actionResolverFactory.getActionForType(MailConfirmationProcessorForRegistrationImpl.class);
            case PASSWORD_RESET -> actionResolverFactory.getActionForType(MailConfirmationProcessorForPasswordChangeImpl.class);
            case ADVERTISEMENT -> actionResolverFactory.getActionForType(MailConfirmationProcessorForAdvertisementImpl.class);
        };
    }
}
