package com.special.gift.app.service.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.special.gift.app.domain.ApplicationMenu;
import com.special.gift.app.domain.Role;
import com.special.gift.app.repository.ApplicationMenuRepository;
import com.special.gift.app.repository.RoleRepository;
import com.special.gift.app.service.ApplicationMenuService;

@Service
@Transactional(readOnly = true)
public class ApplicationMenuServiceBean implements ApplicationMenuService {

  private static final Logger log = LoggerFactory.getLogger(ApplicationMenuServiceBean.class);

  @Autowired
  private ApplicationMenuRepository applicationMenuRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<ApplicationMenu> fetchApplicationMenu() {
    final Iterator<ApplicationMenu> applicationMenuIterator =
        applicationMenuRepository.findAll().iterator();
    final List<ApplicationMenu> applicationMenus = new ArrayList<>();
    while (applicationMenuIterator.hasNext()) {
      applicationMenus.add(applicationMenuIterator.next());
    }
    return applicationMenus;
  }

  @Override
  public List<ApplicationMenu> fetchApplicationMenuByRole(String roleId) {
    final Role role = roleRepository.findOne(roleId);
    return applicationMenuRepository.findApplicationMenuByActiveAndRole(true, role);
  }

  @Override
  @Transactional(readOnly = false)
  public void save(ApplicationMenu applicationMenu) {
    applicationMenuRepository.save(applicationMenu);

  }

  @Override
  public ApplicationMenu findById(String id) {
    // TODO Auto-generated method stub
    try {
      return applicationMenuRepository.findOne(id);
    } catch (final Exception exception) {
      log.debug("exception occured with message : {}", exception.getMessage());
      return null;
    }
  }

  @Override
  @Transactional(readOnly = false)
  public void update(ApplicationMenu applicationMenu) {
    try {
      if (applicationMenuRepository.exists(applicationMenu.getId())) {
        applicationMenuRepository.save(applicationMenu);
      }
    } catch (final Exception exception) {
      log.debug("exception occured with message : {}", exception.getMessage());
    }
  }

}


