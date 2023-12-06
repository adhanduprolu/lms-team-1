package com.lms.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "courselinks")
@JsonPropertyOrder({"videoname,link"})
public class CourseLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "linkid")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long linkid;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "courselinks_link", joinColumns = @JoinColumn(name = "fk_linkid"))
	@Column(name = "link")
	private List<String> link;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "courselinks_videoname", joinColumns = @JoinColumn(name = "fk_linkid"))
	@Column(name = "videoname")
	private List<String> videoname;

}
