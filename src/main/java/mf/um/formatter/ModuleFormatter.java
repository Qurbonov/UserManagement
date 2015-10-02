package mf.um.formatter;

import mf.um.domain.Module;
import mf.um.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by qurbonov on 9/30/2015.
 */
public class ModuleFormatter implements Formatter<Module> {
    @Autowired
    private ModuleService moduleService;

    @Override
    public Module parse(String text, Locale locale) throws ParseException {
        return moduleService.findOne(Long.parseLong(text));
    }

    @Override
    public String print(Module object, Locale locale) {
        return object.getId().toString();
    }
}
