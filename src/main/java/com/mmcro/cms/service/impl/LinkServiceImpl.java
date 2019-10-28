package com.mmcro.cms.service.impl;

import org.springframework.stereotype.Service;

import com.mmcro.cms.comon.CMSRuntimeException;
import com.mmcro.cms.service.LinkService;
import com.mmcro.utils.StringUtils;

@Service
public class LinkServiceImpl implements LinkService{

	@Override
	public int addLink(String url) {
		// TODO Auto-generated method stub
		if(!StringUtils.isUrl(url)) {
			throw new CMSRuntimeException();
		}
		
		return 0;
	}

}
