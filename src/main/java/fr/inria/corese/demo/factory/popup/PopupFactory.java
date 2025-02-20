package fr.inria.corese.demo.factory.popup;

import fr.inria.corese.demo.model.ProjectDataModel;

import java.util.HashMap;
import java.util.Map;

public class PopupFactory {
    public static final String WARNING_POPUP = "warning";
    public static final String FILE_INFO_POPUP = "fileInfo";
    public static final String LOG_POPUP = "log";
    public static final String NEW_FILE_POPUP = "newFile";
    public static final String RULE_INFO_POPUP = "ruleInfo";
    public static final String TOAST_NOTIFICATION = "toast";
    public static final String RENAME_POPUP = "rename";
    public static final String DELETE_POPUP = "delete";
    public static final String CLEAR_GRAPH_CONFIRMATION = "clearGraphConfirmation";
    public static final String SAVE_FILE_CONFIRMATION = "saveFileConfirmation";
    public static final String LOADING_POPUP = "loading";


    private final Map<String, IPopup> popupCache = new HashMap<>();
    private final ProjectDataModel model;

    public PopupFactory(ProjectDataModel model) {
        this.model = model;
    }

    public static PopupFactory getInstance(ProjectDataModel model) {
        return new PopupFactory(model);
    }

    public IPopup createPopup(String type) {
        if (popupCache.containsKey(type)) {
            return popupCache.get(type);
        }

        IPopup popup = switch (type) {
            case WARNING_POPUP -> new WarningPopup();
            case FILE_INFO_POPUP -> new FileInfoPopup();
            case LOG_POPUP -> new LogDialog(model);
            case NEW_FILE_POPUP -> new NewFilePopup();
            case RULE_INFO_POPUP -> new RuleInfoPopup();
            case TOAST_NOTIFICATION -> new ToastNotification();
            case RENAME_POPUP -> new RenamePopup();
            case DELETE_POPUP -> new DeleteConfirmationPopup();
            case CLEAR_GRAPH_CONFIRMATION -> new ClearGraphConfirmationPopup();
            case SAVE_FILE_CONFIRMATION -> new SaveConfirmationPopup();
            case LOADING_POPUP -> new LoadingPopup();
            default -> throw new IllegalArgumentException("Unknown popup type: " + type);
        };

        popupCache.put(type, popup);
        return popup;
    }
}