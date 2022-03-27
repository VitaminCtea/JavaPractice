package permissions;

import java.security.Permission;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WordCheckPermission extends Permission {
    private final String action;

    public WordCheckPermission(String target, String anAction) {
        super(target);
        this.action = anAction;
    }

    @Override
    public String getActions() { return action; }

    @Override
    public boolean equals(Object other) {
        WordCheckPermission copyOther = (WordCheckPermission) other;

        if (other == null) {
            return false;
        }
        if (!getClass().equals(other.getClass())) {
            return false;
        }

        if (!Objects.equals(action, copyOther.action)) {
            return false;
        }

        if ("insert".equals(action)) {
            return Objects.equals(getName(), copyOther.getName());
        } else if ("avoid".equals(action)) {
            return badWordSet().equals(copyOther.badWordSet());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() { return Objects.hash(getName(), action); }

    @Override
    public boolean implies(Permission other) {
        if (!(other instanceof WordCheckPermission copyOther)) {
            return false;
        }

        if ("insert".equals(action)) {
            return "insert".equals(copyOther.action) && getName().contains(copyOther.getName());
        } else if ("avoid".equals(action)) {
            if ("avoid".equals(copyOther.action)) {
                return copyOther.badWordSet().containsAll(badWordSet());
            } else if ("insert".equals(copyOther.action)) {
                for (String badWord: badWordSet()) {
                    if (copyOther.getName().contains(badWord)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public Set<String> badWordSet() { return new HashSet<>(List.of(getName().split(","))); }
}