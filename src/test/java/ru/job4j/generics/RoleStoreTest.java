package ru.job4j.generics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleStoreTest {

    @Test
    void whenAddedAdminRoleThenFindAdminRole() {
        var expected = new Role("1", "admin");
        RoleStore roleStore = new RoleStore();
        roleStore.add(expected);
        Role result = roleStore.findById("1");
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenAddedRoleExistThenNotAdded() {
        var adminRole = new Role("1", "admin");
        var subAdminRole = new Role("1", "administrator");
        RoleStore roleStore = new RoleStore();
        roleStore.add(adminRole);
        roleStore.add(subAdminRole);
        Role result = roleStore.findById("1");
        assertThat(result).isEqualTo(adminRole);
    }

    @Test
    void whenReplaceRoleThenTrue() {
        var adminRole = new Role("1", "admin");
        var subAdminRole = new Role("1", "administrator");
        RoleStore roleStore = new RoleStore();
        roleStore.add(adminRole);
        boolean result = roleStore.replace(subAdminRole.getId(), subAdminRole);
        assertThat(result).isTrue();
    }

    @Test
    void whenDeleteRoleThenNull() {
        var adminRole = new Role("1", "admin");
        RoleStore roleStore = new RoleStore();
        roleStore.add(adminRole);
        roleStore.delete("1");
        Role result = roleStore.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenFindRoleThenSuccessful() {
        var adminRole = new Role("1", "admin");
        var userRole = new Role("2", "user");
        RoleStore roleStore = new RoleStore();
        roleStore.add(adminRole);
        roleStore.add(userRole);
        roleStore.delete("2");
        Role result1 = roleStore.findById("1");
        Role result2 = roleStore.findById("2");
        assertThat(result1).isEqualTo(adminRole);
        assertThat(result2).isNull();
    }
}