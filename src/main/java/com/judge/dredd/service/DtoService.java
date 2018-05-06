package com.judge.dredd.service;

import com.judge.dredd.dto.CriteriaDTO;
import com.judge.dredd.dto.EntryDTO;
import com.judge.dredd.dto.EventDetailDTO;
import com.judge.dredd.dto.ScoreDTO;
import com.judge.dredd.dto.UserDTO;
import com.judge.dredd.model.Criteria;
import com.judge.dredd.model.Entry;
import com.judge.dredd.model.EventDetail;
import com.judge.dredd.model.Score;
import com.judge.dredd.model.SystemUser;

public interface DtoService {

   public Criteria convertToModel(CriteriaDTO criteriaDTO);

   public CriteriaDTO convertToDTO(Criteria criteria);

   public Entry convertToModel(EntryDTO entryDTO);

   public EntryDTO convertToDTO(Entry criteria);

   public EventDetail convertToModel(EventDetailDTO eventDetailDTO);

   public EventDetailDTO convertToDTO(EventDetail eventDetail);

   public Score convertToModel(ScoreDTO eventDetailDTO);

   public ScoreDTO convertToDTO(Score score);
   
   public SystemUser convertToModel(UserDTO userDTO);

   public UserDTO convertToDTO(SystemUser systemUser);
}
