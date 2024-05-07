package me.offsetpaladin89.MoreArmors.enums;

public enum PermissionType {
	/** Give permission */
	GIVE,

	/** Edit permission */
	EDIT,

	/** Reload permission */
	RELOAD,

	/** GUI permission */
	GUI;

	/**
	 * Returns the String interpretation of the PermissionType type.
	 * @param type the PermissionType to get the String interpretation of
	 * @return the String interpretation of the PermissionType type
	 */
	public static String getPermission(PermissionType type) {
		return switch (type) {
			case GIVE -> "morearmors.give";
			case EDIT -> "morearmors.edit";
			case RELOAD -> "morearmors.reload";
			case GUI -> "morearmors.gui";
		};
	}
}
