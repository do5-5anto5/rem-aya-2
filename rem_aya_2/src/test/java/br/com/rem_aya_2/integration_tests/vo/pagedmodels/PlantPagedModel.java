package br.com.rem_aya_2.integration_tests.vo.pagedmodels;

import java.util.List;

import br.com.rem_aya_2.integration_tests.vo.PlantVO;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PlantPagedModel")
public class PlantPagedModel {

	@XmlElement(name = "content")
	private List<PlantVO> content;
	
	public PlantPagedModel() {}

	public List<PlantVO> getContent() {
		return content;
	}

	public void setContent(List<PlantVO> content) {
		this.content = content;
	}
}
