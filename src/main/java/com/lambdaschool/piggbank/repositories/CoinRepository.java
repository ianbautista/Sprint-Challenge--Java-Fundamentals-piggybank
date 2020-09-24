package com.lambdaschool.piggbank.repositories;

import com.lambdaschool.piggbank.models.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository  extends CrudRepository<Coin, Long>
{

}
