public class EnumPermission {

    public enum UserRole {
        ADMIN("拥有所有权限，包括用户管理、数据修改等"),
        USER("可以查看和修改自己的数据"),
        GUEST("只能查看公开信息");
        private final String permissionDesc;
        UserRole(String permissionDesc) {
            this.permissionDesc = permissionDesc;
        }
        public String getPermissionDesc() {
            return permissionDesc;
        }
    }
    public static void main(String[] args) {
        showPermission(UserRole.ADMIN);
        showPermission(UserRole.USER);
        showPermission(UserRole.GUEST);
    }
    public static void showPermission(UserRole role) {
        System.out.println(role.name() + ": " + role.getPermissionDesc());
    }
}