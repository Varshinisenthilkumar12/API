package com.job.services;
import java.util.ArrayList;
import java.util.List;

import com.job.model.Organization;

public class OrganizationService {
    private List<Organization> organizationList;

    public OrganizationService() {
        this.organizationList = new ArrayList<>();
    }

    public void addOrganization(Organization organization) {
        organizationList.add(organization);
    }

    public List<Organization> getAllOrganizations() {
        return organizationList;
    }

    public Organization getOrganizationByName(String name) {
        for (Organization org : organizationList) {
            if (org.getName().equals(name)) {
                return org;
            }
        }
        return null; 
    }

    public boolean deleteOrganization(String name) {
        Organization org = getOrganizationByName(name);
        if (org != null) {
            organizationList.remove(org);
            return true; 
        }
        return false; 
    }
}
