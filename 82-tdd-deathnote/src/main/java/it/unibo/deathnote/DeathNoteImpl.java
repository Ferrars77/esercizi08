package it.unibo.deathnote;

import java.util.List;
import it.unibo.deathnote.api.DeathNote;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class DeathNoteImpl implements DeathNote {

    private final Map<String, Death> deaths;
    private String lastNameWritten;

    public DeathNoteImpl() {
        deaths = new LinkedHashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {

        if(ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("RULES MUST START FROM 1 AND NOT BE 0 OR NEGATIVE (OR HIGHER THAN THE RULES NUMBER)");
        }
        return RULES.get(ruleNumber-1);
        
    }

    @Override
    public void writeName(String name) {
        if(name == null) {
            throw new NullPointerException("NAME CANT BE NULL");
        } 
        deaths.put(name, new Death());
        lastNameWritten = name;
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if(lastNameWritten == null || cause == null) {
            throw new IllegalStateException("THE NAME IS NOT IN THE BOOK");
        }
        final Death lastDeath = deaths.get(lastNameWritten);
        final long timeFinal = System.currentTimeMillis() - lastDeath.timeOfDeath;
        if(timeFinal <= 40) {
            lastDeath.cause = cause;
            lastDeath.timeOfDeathCause = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean writeDetails(String details) {
        if(lastNameWritten == null || details == null) {
            throw new IllegalStateException("THE NAME IS NOT IN THE BOOK");
        }
        final Death lastDeath = deaths.get(lastNameWritten);
        final long timeFinal = System.currentTimeMillis() - lastDeath.timeOfDeathCause;
        if(timeFinal <= 6040) {
            lastDeath.details = details;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getDeathCause(String name) {
        if(!deaths.containsKey(name)) {
            throw new IllegalArgumentException("THE NAME IS NOT IN THE BOOK");
        }
        return deaths.get(name).cause;  
    }

    @Override
    public String getDeathDetails(String name) {
        if(!deaths.containsKey(name)) {
            throw new IllegalArgumentException("THE NAME IS NOT IN THE BOOK");
        }
        return deaths.get(name).details; 
    }

    @Override
    public boolean isNameWritten(String name) {
        return deaths.containsKey(name);
    }

    private static final class Death {
        private static final String DEFAULT_CAUSE = "heart attack";
        private static final byte VALID_CAUSE_TIMEOUT = 40;
        private static final short VALID_DETAILS_TIMEOUT = 6000 + VALID_CAUSE_TIMEOUT;
        private String cause;
        private String details;
        private final long timeOfDeath;
        private long timeOfDeathCause;

        private Death(final String cause, final String details) {
            this.cause = cause;
            this.details = details;
            this.timeOfDeath = System.currentTimeMillis();
            this.timeOfDeathCause = this.timeOfDeath;
        }
        Death() {
            this(DEFAULT_CAUSE, "");
        }
      

        

    }
}
