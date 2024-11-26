package com.example.musicwhisky1.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.musicwhisky1.model.Musica;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MusicaDao_Impl implements MusicaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Musica> __insertionAdapterOfMusica;

  private final EntityDeletionOrUpdateAdapter<Musica> __deletionAdapterOfMusica;

  private final EntityDeletionOrUpdateAdapter<Musica> __updateAdapterOfMusica;

  public MusicaDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMusica = new EntityInsertionAdapter<Musica>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `musicas` (`id`,`idAlbum`,`idArtista`,`nome`,`duracao`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Musica entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdAlbum());
        statement.bindLong(3, entity.getIdArtista());
        statement.bindString(4, entity.getNome());
        statement.bindLong(5, entity.getDuracao());
      }
    };
    this.__deletionAdapterOfMusica = new EntityDeletionOrUpdateAdapter<Musica>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `musicas` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Musica entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMusica = new EntityDeletionOrUpdateAdapter<Musica>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `musicas` SET `id` = ?,`idAlbum` = ?,`idArtista` = ?,`nome` = ?,`duracao` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Musica entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getIdAlbum());
        statement.bindLong(3, entity.getIdArtista());
        statement.bindString(4, entity.getNome());
        statement.bindLong(5, entity.getDuracao());
        statement.bindLong(6, entity.getId());
      }
    };
  }

  @Override
  public Object inserir(final Musica musica, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMusica.insert(musica);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final List<Musica> artistas, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMusica.insert(artistas);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletar(final Musica musica, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMusica.handle(musica);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object atualizar(final Musica musica, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMusica.handle(musica);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object buscarTodos(final Continuation<? super List<Musica>> $completion) {
    final String _sql = "SELECT * FROM musicas";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Musica>>() {
      @Override
      @NonNull
      public List<Musica> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "idAlbum");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDuracao = CursorUtil.getColumnIndexOrThrow(_cursor, "duracao");
          final List<Musica> _result = new ArrayList<Musica>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Musica _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpIdAlbum;
            _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDuracao;
            _tmpDuracao = _cursor.getInt(_cursorIndexOfDuracao);
            _item = new Musica(_tmpId,_tmpIdAlbum,_tmpIdArtista,_tmpNome,_tmpDuracao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<Musica> ListarPorId(final int musicaId) {
    final String _sql = "SELECT * FROM musicas WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, musicaId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"musicas"}, false, new Callable<Musica>() {
      @Override
      @Nullable
      public Musica call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "idAlbum");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDuracao = CursorUtil.getColumnIndexOrThrow(_cursor, "duracao");
          final Musica _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpIdAlbum;
            _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDuracao;
            _tmpDuracao = _cursor.getInt(_cursorIndexOfDuracao);
            _result = new Musica(_tmpId,_tmpIdAlbum,_tmpIdArtista,_tmpNome,_tmpDuracao);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Musica>> buscarPorNome(final String nome) {
    final String _sql = "SELECT * FROM musicas WHERE nome LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, nome);
    return __db.getInvalidationTracker().createLiveData(new String[] {"musicas"}, false, new Callable<List<Musica>>() {
      @Override
      @Nullable
      public List<Musica> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfIdAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "idAlbum");
          final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
          final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
          final int _cursorIndexOfDuracao = CursorUtil.getColumnIndexOrThrow(_cursor, "duracao");
          final List<Musica> _result = new ArrayList<Musica>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Musica _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpIdAlbum;
            _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
            final int _tmpIdArtista;
            _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
            final String _tmpNome;
            _tmpNome = _cursor.getString(_cursorIndexOfNome);
            final int _tmpDuracao;
            _tmpDuracao = _cursor.getInt(_cursorIndexOfDuracao);
            _item = new Musica(_tmpId,_tmpIdAlbum,_tmpIdArtista,_tmpNome,_tmpDuracao);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Musica> musicasAlbum(final int idAlbum) {
    final String _sql = "SELECT * FROM musicas WHERE idAlbum = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idAlbum);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfIdAlbum = CursorUtil.getColumnIndexOrThrow(_cursor, "idAlbum");
      final int _cursorIndexOfIdArtista = CursorUtil.getColumnIndexOrThrow(_cursor, "idArtista");
      final int _cursorIndexOfNome = CursorUtil.getColumnIndexOrThrow(_cursor, "nome");
      final int _cursorIndexOfDuracao = CursorUtil.getColumnIndexOrThrow(_cursor, "duracao");
      final List<Musica> _result = new ArrayList<Musica>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final Musica _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final int _tmpIdAlbum;
        _tmpIdAlbum = _cursor.getInt(_cursorIndexOfIdAlbum);
        final int _tmpIdArtista;
        _tmpIdArtista = _cursor.getInt(_cursorIndexOfIdArtista);
        final String _tmpNome;
        _tmpNome = _cursor.getString(_cursorIndexOfNome);
        final int _tmpDuracao;
        _tmpDuracao = _cursor.getInt(_cursorIndexOfDuracao);
        _item = new Musica(_tmpId,_tmpIdAlbum,_tmpIdArtista,_tmpNome,_tmpDuracao);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
