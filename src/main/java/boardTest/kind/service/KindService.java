package boardTest.kind.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import boardTest.kind.dao.KindDaoI;
import boardTest.kind.model.KindVo;

@Service("kindService")
public class KindService implements KindServiceI{
	@Resource(name="kindDao")
	private KindDaoI dao;
	
	@Override
	public List<KindVo> roadMenuList() {
		return dao.roadMenuList();
	}

	@Override
	public int changeStatus(Map<String, String> kindInfo) {
		return dao.changeStatus(kindInfo);
	}

	@Override
	public int kindRegist(KindVo kindVo) {
		return dao.kindRegist(kindVo);
	}

	@Override
	public KindVo readKindOne(String kind_no) {
		return dao.readKindOne(kind_no);
	}

}
