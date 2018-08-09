package com.judge.dredd.service;

import com.judge.dredd.dto.CategoryDTO;
import com.judge.dredd.dto.CommentsDTO;
import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.CriteriaScoreDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDTO;
import com.judge.dredd.dto.MemberDTO;
import com.judge.dredd.dto.RateDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.dto.SettingsDTO;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.AppUser;
import com.judge.dredd.model.Category;
import com.judge.dredd.model.Comments;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.Event;
import com.judge.dredd.model.Member;
import com.judge.dredd.model.Score;
import com.judge.dredd.model.Settings;
import com.judge.dredd.model.Tabulator;

public interface DtoService {

   public Criteria convertToModel(CriteriaDTO criteriaDTO);

   public CriteriaDTO convertToDTO(Criteria criteria);

   public Entry convertToModel(EntryDTO entryDTO);

   public EntryDTO convertToDTO(Entry criteria);
   
   public Score convertToModel(ScoreDTO eventDetailDTO);

   public ScoreDTO convertToDTO(Score score);
   
   public AppUser convertToModel(UserDTO userDTO);

   public UserDTO convertToDTO(AppUser systemUser);
   
   public Member convertToModel(MemberDTO memberDTO);
   
   public MemberDTO convertToDTO(Member member);
   
   public Event convertToModel(EventDTO eventDTO);
   
   public EventDTO convertToDTO(Event event);
   
   public Category convertToModel(CategoryDTO categoryDTO);
   
   public CategoryDTO convertToDTO(Category category);
   
   public Comments convertToModel(CommentsDTO commentsDTO);
   
   public CommentsDTO convertToDTO(Comments comment);
   
   public CriteriaScoreDTO convertToMixDTO(Score score);
   
   public RateDTO convertToDTO(Tabulator tabulator);
   
   public Settings convertToModel(SettingsDTO commentsDTO);
   
   public SettingsDTO convertToDTO(Settings comment);
}
